import os
import playsound
from gtts import gTTS

language = "en"
file_name = 'myfile.mp3'

def read_from_file():
    f = open("instructions.txt","r")
    file_text = f.read()
    f.close()

    audio_created = gTTS(text=file_text,lang=language,slow=False)
    audio_created.save(file_name)

    playsound.playsound(file_name,True)

if __name__ == "__main__":
    read_from_file()