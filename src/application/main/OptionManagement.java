package main;

public class OptionManagement
{
    //Option 1: Translate word
    public static void translateWord()
    {
        System.out.print("Enter what you want to translate (En-Vi): ");
        String text=Reader.readLine();
        System.out.println(text);
    }

    //Option 0: Exit app
    public static void exitApp()
    {
        System.out.println("Thank you for choosing us");
        System.exit(0);
    }
}
