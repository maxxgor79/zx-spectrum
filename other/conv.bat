native-image.exe -jar %1% z80asm --no-fallback -H:IncludeResourceBundles=i18n.Messages -H:IncludeResources=template/z80/[a-zA-Z]+.op$ -H:IncludeResources=settings.properties$ -H:IncludeResources=settings.properties$ -H:IncludeResources=log4j.properties$