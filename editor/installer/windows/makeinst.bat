jpackage -i app --name RetroIDE --main-jar editor-1.0-jar-with-dependencies.jar --main-class ru.retro.assembler.editor.core.Editor --type app-image --verbose --icon res\microchip.ico --dest dest --java-options -Dz80asm.embedded=true
