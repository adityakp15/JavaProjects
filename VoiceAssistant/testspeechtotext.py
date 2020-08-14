import os
import speech_recognition as sr
import wolframalpha
import playsound
from gtts import gTTS
from selenium import webdriver

language = "en-us"
num = 1

r = sr.Recognizer()
mic = sr.Microphone()

with mic as source:
    print("speak")
    r.adjust_for_ambient_noise(source)
    audio = r.listen(source)

text = r.recognize_google(audio,language="en-us")
print("You : ",text)