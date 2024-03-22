package ru.retro.assembler.editor.core.audio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * SDLPlayer.
 *
 * @author Maxim Gorin
 */
@Slf4j
public class SDLPlayer implements AudioPlayer, LineListener {

  private File file;

  private AudioState state = AudioState.Initialized;

  private AudioInputStream audioStream;

  private AudioFormat audioFormat;

  private SourceDataLine sourceDataLine;

  private AudioPlayerEvent handler;

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  public SDLPlayer(AudioPlayerEvent handler) {
    this.handler = handler;
  }

  @Override
  public void setFile(@NonNull final File file) throws IOException, AudioPlayerException {
    this.file = file;
    try {
      audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(IOUtils.toByteArray
          (new FileInputStream(file))));
      audioFormat = audioStream.getFormat();
      final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
      sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
      sourceDataLine.addLineListener(this);
      sourceDataLine.open(audioFormat);
    } catch (UnsupportedAudioFileException | LineUnavailableException e) {
      log.error(e.getMessage(), e);
      throw new AudioPlayerException(e);
    }
  }

  @Override
  public File getFile() {
    return file;
  }

  @Override
  public void start() {
    sourceDataLine.start();
    executorService.execute(() -> {
      int readBytes;
      final byte[] buffer = new byte[4096];
      try {
        while ((readBytes = audioStream.read(buffer)) != -1) {
          sourceDataLine.write(buffer, 0, readBytes);
          if (handler != null) {
            handler.received(SDLPlayer.this, buffer, 0, readBytes);
          }
        }
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
      stop();
    });
  }

  @Override
  public void stop() {
    if (sourceDataLine != null) {
      sourceDataLine.stop();
      try {
        audioStream.reset();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }
  }

  @Override
  public void close() {
    if (sourceDataLine != null) {
      sourceDataLine.close();
      sourceDataLine = null;
    }
    IOUtils.closeQuietly(audioStream);
    audioStream = null;
    audioFormat = null;
    executorService.shutdownNow();
  }

  @Override
  public AudioFormat getFormat() {
    return audioFormat;
  }

  @Override
  public AudioState getState() {
    return state;
  }

  @Override
  public long getFrameLength() {
    return audioStream == null ? 0 : audioStream.getFrameLength();
  }

  @Override
  public long getFramePosition() {
    return sourceDataLine == null ? 0 : sourceDataLine.getLongFramePosition();
  }

  @Override
  public void update(LineEvent e) {
    if (LineEvent.Type.OPEN == e.getType()) {
      log.info("Playback started.");
      state = AudioState.Open;
      if (handler != null) {
        handler.open(this);
      }
    } else if (LineEvent.Type.START == e.getType()) {
      log.info("Playback started.");
      state = AudioState.Started;
      if (handler != null) {
        handler.started(this);
      }
    } else if (LineEvent.Type.STOP == e.getType()) {
      log.info("Playback stopped.");
      state = AudioState.Stopped;
      if (handler != null) {
        handler.stopped(this);
      }
    } else if (LineEvent.Type.CLOSE == e.getType()) {
      log.info("Playback closed.");
      state = AudioState.Closed;
      if (handler != null) {
        handler.closed(this);
      }
    }
  }
}
