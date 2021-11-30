import requests
import json

def update_fan(houseID, floorID, fan):
    url = "http://127.0.0.1/49ersense/home/updatefan.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "fan": fan
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def update_mode(houseID, floorID, mode):
    url = "http://127.0.0.1/49ersense/home/updatemode.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "mode": mode
            }
    r = requests.post(url=url,data=data)
    print(r.text)


def update_temperature(houseID, floorID, control_temp):
    url = "http://127.0.0.1/49ersense/home/updatecontroltemp.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "controlTemp": control_temp
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def main():
    fan = ["Off", "Auto"]
    mode = ["Off","Cool"]
    control_temp = [68,74]
    houseID = 1
    floorID = [1,2]

    for i in range(len(fan)):
        update_fan(houseID, floorID[i], fan[i])

    for i in range(len(mode)):
        update_mode(houseID, floorID[i], mode[i])

    for i in range(len(control_temp)):
        update_temperature(houseID, floorID[i], control_temp[i])

if __name__=="__main__":
    main()
