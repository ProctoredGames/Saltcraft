package net.proctoredgames.saltcraft.client;

public class ClientThirstData {
    private static int thirst;

    public static void setThirst(int p_thirst) {
        ClientThirstData.thirst = p_thirst;
    }

    public static int getThirst() {
        return thirst;
    }
}