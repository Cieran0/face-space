#!/usr/bin/python3
import platform
import os
import sys

def default():
    os.system("make all >> build.log")

    if(windows):
        os.system("del *.class >> build.log")
    else:
        os.system("rm *.class") 

def run():
    os.system("make default >> build.log")
    os.system("java -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true -jar Main.jar")

def all(): 
	os.system("javac -d . *.java")
	os.system("jar cmvf MANIFEST.MF Main.jar *.class")

def clean():

    if(windows):
        os.system("del *.jar")
        os.system("del *.class")
    else:
        os.system("rm *.jar")
        os.system("rm *.class")


windows = False
logPath = "build.log"
if(platform.system() != "Linux"):
    windows = True

profile = "default"
if(len(sys.argv) > 1):
    profile = sys.argv[1]
if(profile == "run"):
    run()
elif(profile == "all"):
    all()
elif(profile == "clean"):
    clean()
else:
    default() 

