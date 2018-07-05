package com.example.egebe.worldcup2018;

class WorldCupDataSingleton {
    private static WorldCupDataSingleton ourInstance = null;

    public static WorldCupDataSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new WorldCupDataSingleton();
        }

        return ourInstance;
    }

    private WorldCupResponse worldCupResponse;

    private WorldCupDataSingleton() {
    }

    public void setWorldCupResponse(WorldCupResponse response) {
        this.worldCupResponse = response;
    }

    public WorldCupResponse getWorldCupResponse() {
        return worldCupResponse;
    }
}
