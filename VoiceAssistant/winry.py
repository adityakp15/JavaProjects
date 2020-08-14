import os
import speech_recognition as sr
import wolframalpha
import playsound
from gtts import gTTS
from selenium import webdriver

num = 1

def winry_speaks(output):
    global num
    num += 1
    print("Winry : ",output)
    toSpeak = gTTS(text=output,lang="en-us",slow=False)
    file = str(num)+".mp3"
    toSpeak.save(file)

    playsound.playsound(file)
    os.remove(file)

def speech_to_text():
    r = sr.Recognizer()
    mic = sr.Microphone()

    with mic as source:
        #winry_speaks("What would you like me to do ? ")
        print("Speak....")
        r.adjust_for_ambient_noise(source)
        audio = r.listen(source,phrase_time_limit=3)
    print("Gotcha ! ")
    #winry_speaks("Okay, thats enough now.")

    try:
        text = r.recognize_google(audio,language="en-us")
        print("You :",text)
        print("Processing....")
        return text
    except:
        winry_speaks("I couldn't understand anything, you fool.")
        return 0

if __name__=="__main__":
    winry_speaks("Hi my name is Winry, what is yours ? ")
    name = speech_to_text()
    winry_speaks("Hello " + name )







