import requests
import json

base_url = "https://pokeapi.co/api/v2/"

def get_pokemon_data(choice):

    base = base_url + choice
    params = {}
    params["offest"] = 0
    params["limit"] = 964
    resp = requests.get(base,params)
    #print(resp.json())
    #print(resp.url)
    print_details(resp.json(),params["limit"],choice)

def get_berry_data(choice):

    base =  base_url + choice
    params = {}
    params["offest"] = 0
    params["limit"] = 64
    resp = requests.get(base,params)
    print(resp.url)
    #print(resp.json())
    print_details(resp.json(),params["limit"],choice)

def get_evolution_data(choice):

    base =  base_url + choice
    params = {}
    params["offest"] = 0
    params["limit"] = 419
    resp = requests.get(base,params)
    #print(resp.url)
    #print(resp.json())
    data = resp.json()
    for i in range(0,params["limit"]):
        #print(data["results"][i]["url"])
        ev_data = requests.get(data["results"][i]["url"])
        d = ev_data.json()
        print(d["chain"]["species"]["name"])
        print(d["chain"]["evolves_to"][0]["species"]["name"])
        print(d["chain"]["evolves_to"][0]["evolves_to"][0]["species"]["name"])
        

def print_details(data,limit,choice):

    if(choice == "pokemon"):
        for i in range(0,limit):
            print("Pokemon #{} is {}".format(i+1,data["results"][i]["name"].upper()))
    elif(choice == "berry"):
        for i in range(0,limit):
            print("The available berries are {} berry".format(data["results"][i]["name"].upper()))

def get_choice():

    print("What would you like to know ? ")
    choices = {
        1 : "Pokemon",
        2 : "Evolution",
        3 : "Berries",
        4 : "Quit"
    }

    for choice in choices:
        print(choice,": ",choices[choice])

    choice = int(input("Enter your choice : "))
    if(choice == 1):
        get_pokemon_data("pokemon")
    elif(choice == 2):
        get_evolution_data("evolution-chain")
    elif(choice == 3):
        get_berry_data("berry")
    elif(choice == 4):
        quit()
    
    
    

def main():
    
    get_choice()

main()