# FIRST BUILD THE JAR, THEN RUN IN THE SAME DIRECTORY TO BUILD A WINDOWS EXECUTABLE
# oh and you also need wixtools. nothing is ever too easy
jpackage --input . --name 'Memory Maze' --main-jar Muistipeli-1.0-SNAPSHOT.jar --main-class Start --type msi --java-options "--enable-preview" --win-dir-chooser --icon .\newCube.ico --win-shortcut
