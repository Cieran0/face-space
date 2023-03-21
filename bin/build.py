#!/usr/bin/python3
import platform
import os
import sys

def default():
    all()

    if(windows):
        os.system("del *.class >> build.log")
    else:
        os.system("rm *.class") 

def run():
    default()
    print("Makefile Done!")
    os.chdir("../")
    if(windows):
        os.system("java -jar Main.jar")
    else:
        os.system("java -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true -jar Main.jar")

def all(): 
	os.system("javac -d . *.java")
	os.system("jar cmvf MANIFEST.MF ../Main.jar *.class")

def clean():

    if(windows):
        os.system("del ../*.jar")
        os.system("del *.class")
    else:
        os.system("rm ../*.jar")
        os.system("rm *.class")


windows = False
logPath = "build.log"
if(platform.system() != "Linux"):
    windows = True

profile = "default"

if(windows):
    os.system("del ..\*.jar")
else:
    os.system("rm ../*.jar") 

if(len(sys.argv) > 1):
    profile = sys.argv[1]
if(profile == "run"):
    run()
elif(profile == "all"):
    all()
    print("Makefile Done!")
elif(profile == "clean"):
    clean()
    print("Makefile Done!")
else:
    default() 
    print("Makefile Done!")
