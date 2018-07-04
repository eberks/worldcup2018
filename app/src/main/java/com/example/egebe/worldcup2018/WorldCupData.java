package com.example.egebe.worldcup2018;

class WorldCupData {
    private static WorldCupData ourInstance = null;

     public static WorldCupData getInstance() {
         if(ourInstance == null) {
             ourInstance = new WorldCupData();
         }

        return ourInstance;
    }

    private WorldCupResponse worldCupResponse;

    private WorldCupData() {
    }

    public void setWorldCupResponse(WorldCupResponse response) {
        this.worldCupResponse = response;
    }

    public WorldCupResponse getWorldCupResponse() {
        return worldCupResponse;
    }
}
