# HOW TO
*I'm not turning this into an exe file too keep it OS-independent.*
## Preparation:
1. Get Java 17 here: [https://www.openlogic.com/openjdk-downloads](https://www.openlogic.com/openjdk-downloads?field_java_parent_version_target_id=807&field_architecture_target_id=391&field_java_package_target_id=401)  
   Select your operating system, the link should fill out the rest.
3. Download the latest **<ins>JRE</ins>** release and install it / unpack it somewhere, depending on whether you picked the installer or archive.
   
## Run the app
<a href="https://github.com/CraftyApe/copypasta/blob/master/howto.png?raw=true" target="_blank">
    <img src="https://github.com/CraftyApe/copypasta/blob/master/howto.png?raw=true" alt="Screenshots showing the following steps 1-4">
</a>

### Windows
1. Create a shortcut to CopyPasta.jar
2. Open the shortcut's properties *(see screenshot)*
3. If you used the installer, put "javaw -jar" before the path to CopyPasta.jar  
   If you unpacked the JRE instead, put the full path to "javaw.exe -jar" before it
4. Run it! \o/

If you want to start it via the "run" function of Windows
- javaw -jar "C:\Some Folder\CopyPasta.jar" - if Java was installed
- PATH\javaw.exe -jar "C:\Some Folder\CopyPasta.jar" - if Java was unpacked. In this case, PATH needs to be the path to javaw.exe

### If not on Windows, you got two options:
- Run it via "<path-to-java-executable> -jar CopyPasta.jar"
- Make yourself a nice shell script for it. :)
