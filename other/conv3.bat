C:\Users\Maxim\works\graalvm-jdk-21.0.1.12.1\bin\native-image -jar %1 z80asm -H:ConfigurationFileDirectories=conf --no-fallback -H:IncludeResourceBundles=i18n.Messages -H:IncludeResources=template/z80/[a-zA-Z]+.op$ -H:IncludeResources=log4j.properties$ -H:IncludeResources=settings.properties$