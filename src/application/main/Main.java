package main;

public class Main
{
    private static final int EXIT=0;
    private static final int TRANSLATE=1;

    public static void displayOptions()
    {
        System.out.println("Welcome to my English - Vietnamese Dictionary App");
        System.out.println("[0] = Exit");
        System.out.println("[1] = Google Translate");
        System.out.print("Enter an option (0 - 10): ");
    }

    public static void handleOptions(int option)
    {
        switch (option)
        {
            case EXIT -> OptionManagement.exitApp();
            case TRANSLATE -> OptionManagement.translateWord();
            default -> System.out.println("Invalid option, please enter an option in range 0 to 10");
        }
    }

    public static void main(String[] args)
    {
        displayOptions();
        int option;
        option=Reader.readInt();
        Reader.readLine(); //skip \n symbol when read integer
        handleOptions(option);
    }
}
