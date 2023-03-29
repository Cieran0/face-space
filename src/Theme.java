import java.awt.Color;

public abstract class Theme {

    public static int themeID = 0;

    public static Color PRIMARY_BG = new Color(0x494949);
    public static Color SECONDARY_BG = new Color(0x2D2D2D);
    public static Color ACCENT_BG = new Color(0x252525);
    public static Color PRIMARY_FG = new Color(0xFFFFFF);
    public static Color SECONDARY_FG = new Color(0xFFFFFF);
    public static Color BUTTON_BG_1 = new Color(0xF1B434);
    public static Color BUTTON_BG_2 = new Color(0xF68A2A);
    public static Color BUTTON_TEXT = new Color(0xFFFFFF);
    public static Color BUTTON_SELECTED = new Color(0x000000);

    public static void changeTheme(int newThemeID){
        themeID = newThemeID;
        switch(themeID){ 
            case 0:
                PRIMARY_BG = new Color(0x494949);
                SECONDARY_BG = new Color(0x2D2D2D);
                ACCENT_BG = new Color(0x252525);
                PRIMARY_FG = new Color(0xFFFFFF);
                SECONDARY_FG = new Color(0xFFFFFF);
                BUTTON_BG_1 = new Color(0xF1B434);
                BUTTON_BG_2 = new Color(0xF68A2A);
                BUTTON_TEXT = new Color(0xFFFFFF);
                BUTTON_SELECTED = new Color(0x000000);
                break;
            case 1:
                PRIMARY_BG = Color.LIGHT_GRAY;
                SECONDARY_BG = Color.DARK_GRAY;
                ACCENT_BG = Color.GRAY;
                PRIMARY_FG = Color.BLACK;
                SECONDARY_FG = Color.WHITE;
                BUTTON_BG_1 = Color.WHITE;
                BUTTON_BG_2 = Color.LIGHT_GRAY;
                BUTTON_TEXT = Color.DARK_GRAY;
                BUTTON_SELECTED = Color.WHITE;
                break;
            case 2:
                PRIMARY_BG = new Color(0xD98484);
                SECONDARY_BG = new Color(0x542C2C);
                ACCENT_BG = new Color(0x985151);
                PRIMARY_FG = new Color(0xFFFFFF);
                SECONDARY_FG = new Color(0xFFFFFF);
                BUTTON_BG_1 = new Color(0xF1B434);
                BUTTON_BG_2 = new Color(0xF68A2A);
                BUTTON_TEXT = new Color(0x000000);
                BUTTON_SELECTED = Color.WHITE;
                break;
            case 3:
                PRIMARY_BG = new Color(0x84B2D9);
                SECONDARY_BG = new Color(0x2C4454);
                ACCENT_BG = new Color(0x517D98);
                PRIMARY_FG = Color.BLACK;
                SECONDARY_FG = new Color(0xFFFFFF);
                BUTTON_BG_1 = new Color(0xC2C2C2);
                BUTTON_BG_2 = new Color(0xC2C2C2);
                BUTTON_TEXT = new Color(0x000000);
                BUTTON_SELECTED = Color.WHITE;
                break;
            case 4:
                PRIMARY_BG = new Color(0x0067F1);
                SECONDARY_BG = new Color(0xFF8100);
                ACCENT_BG = new Color(0x858484);
                PRIMARY_FG = new Color(0xFFFFFF);
                SECONDARY_FG = new Color(0xFFFFFF);
                BUTTON_BG_1 = new Color(0x858484);
                BUTTON_BG_2 = new Color(0xFFFFFF);
                BUTTON_TEXT = new Color(0x000000);
                BUTTON_SELECTED = new Color(0xFFFFFF);
                break;
        }
    }
}
