import requests
import json


def get_data(name):

    base_url = "https://itunes.apple.com/search"
    params = {}
    params["term"] = name
    params["media"] = "music"
    #params["sort"] = "recent"
    #params["limit"] = 5
    #params["artistId"] = 489704062
    #params["entity"] = "music"

    resp = requests.get(base_url,params)
    #print(resp.url)
    #webbrowser.open(resp.url)
    #print(json.dumps(resp.json(), indent = 2))
    #print(resp.status_code)

    print_tracknames(resp.json())

def print_tracknames(songs):

    for num in range(songs["resultCount"]):
        print(num+1,songs["results"][num]["trackName"])

name = input("Enter Artist Name : ")
name = "{}".format(name)

get_data(name)
