default:
	@make all >> /dev/null
	@rm *.class
run:
	@make default >> /dev/null
	@java -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true -jar Main.jar

all: 
	javac -d . *.java
	jar cmvf MANIFEST.MF Main.jar *.class

clean:
	-rm *.jar
	-rm *.class