import requests

base_url = "https://pokeapi.co/api/v2/"

def get_pokemon_data(choice):
    #base_url = "https://pokeapi.co/api/v2/pokemon"
    base = base_url + choice
    params = {}
    params["offest"] = 0
    params["limit"] = 964
    resp = requests.get(base,params)
    #print(resp.json())
    #print(resp.url)
    print_pokemon(resp.json(),params["limit"])

def print_pokemon(data,limit):
    for i in range(0,limit):
        print("Pokemon {} is {}".format(i+1,data["results"][i]["name"]))

def get_choice():
    print("What would you like to know ? ")
    choices = {
        1 : "Pokemon",
        2 : "Evolution",
        3 : "Berries",
        4 : "Location",
        5 : "Moves"
    }

    for choice in choices:
        print(choice,": ",choices[choice])

    choice = int(input("Enter your choice : "))
    if(choice == 1):
        get_pokemon_data("pokemon")

def main():
    
    get_choice()

main()