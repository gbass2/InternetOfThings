import requests
import json

def update_lights(houseID, floorID, lightID, dim_level, light_status):
    url = "http://127.0.0.1/49ersense/home/updatelights.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "lightID": lightID,
            "lightStatus": light_status,
            "dimmerLevel": dim_level
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def main():
    light_status = [1,0,0,1]
    dim_level = [1,3,4,2]
    lightID = [1,2,3,4]
    houseID = 1;
    floorID = [1,1,2,2]

    for i in range(len(lightID)):
        update_lights(houseID, floorID[i], lightID[i], dim_level[i], light_status[i])

if __name__=="__main__":
    main()
