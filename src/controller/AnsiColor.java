package controller;

public class AnsiColor {
    public static final String Reset = "\u001B[0m";
    public static final String Red = "\u001B[91m";
    public static final String Green = "\u001B[92m";
    public static final String BrightBlue = "\u001B[1;34m";
    public static final String Blue = "\u001B[38;2;91;171;255m";
    public static final String Orange = "\u001B[38;5;208m";
    public static final String Yellow = "\u001B[33m";
    public static final String Pink = "\u001B[95m";
    public static final String Cyan = "\u001B[38;5;51m";
    public static final String Purple = "\u001B[38;5;135m";
    public static final String Teal = "\u001B[38;5;37m"; 
    public static final String Gray = "\u001B[38;5;240m";
    public static final String SoftPink = "\u001B[38;2;255;182;193m"; // RGB soft pink
    public static final String Aqua = "\u001B[38;2;0;255;255m"; // Aqua
    public static final String Lime = "\u001B[38;2;0;255;0m"; // Bright green
    public static final String Gold = "\u001B[38;2;255;215;0m"; // Gold
    public static final String SkyBlue = "\u001B[38;2;135;206;250m"; // Sky Blue
    public static final String Coral = "\u001B[38;2;255;127;80m"; // Coral
    public static final String Peach = "\u001B[38;2;255;218;185m"; // Peach
    public static final String Mint = "\u001B[38;2;152;255;152m"; // Mint
    public static final String Lavender = "\u001B[38;2;230;230;250m"; // Lavender
    public static final String Indigo = "\u001B[38;2;75;0;130m"; // Indigo
    public static final String Crimson = "\u001B[38;2;220;20;60m"; // Crimson Red
    public static final String Navy = "\u001B[38;2;0;0;128m"; // Navy
    public static final String Olive = "\u001B[38;2;128;128;0m"; // Olive
    public static final String Chocolate = "\u001B[38;2;210;105;30m"; // Chocolate Brown
    public static final String HotPink = "\u001B[38;2;255;105;180m";
    public static final String NeonGreen = "\u001B[38;2;57;255;20m";
    public static final String ElectricBlue = "\u001B[38;2;44;117;255m";
    public static final String BrightCyan = "\u001B[38;2;0;255;255m";
    public static final String BrightMagenta = "\u001B[38;2;255;0;255m";
    public static final String BrightOrange = "\u001B[38;2;255;165;0m";
    public static final String Lemon = "\u001B[38;2;255;250;100m";
    public static final String BrightTurquoise = "\u001B[38;2;0;245;255m";
    public static final String BrightViolet = "\u001B[38;2;190;0;255m";
    public static final String FluorescentYellow = "\u001B[38;2;255;255;102m";

    public static String colorize(String text, String ansiCode) {
        return ansiCode + text + Reset;
    }
}
