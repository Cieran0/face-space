default:
	@make all >> /dev/null
	@rm -r Gui >> /dev/null

run:
	@make default >> /dev/null
	@java -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true -jar Main.jar

all: 
	javac -d . *.java
	jar cmvf MANIFEST.MF Main.jar Gui/

clean:
	-rm *.jar
	-rm *.class
	-rm -r Gui