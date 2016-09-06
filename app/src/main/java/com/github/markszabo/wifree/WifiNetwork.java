package com.github.markszabo.wifree;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WifiNetwork {
    public static final int CRACK_FINISHED = -1;

    public String SSID;
    public String BSSID;
    public int serialNumber;
    public List<String> possiblePasswords;

    public WifiNetwork(String SSID, String BSSID, int serialNumber, List<String> possiblePasswords){
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.serialNumber = serialNumber;
        this.possiblePasswords = possiblePasswords;
    }

    public WifiNetwork(String SSID, String BSSID) {
        this.SSID = SSID;
        this.BSSID = BSSID;
    }

    public String getVulnerabilityMessage(){
        //Test if SSID starts with UPC followed by 6 digits
        Pattern p = Pattern.compile("^UPC\\d{6}$");
        Matcher m = p.matcher(this.SSID);
        if(!m.find()) {
            return "Not vulnerable, since SSID does not match to UPC\\d{6}";
        }

        /*TODO does not have all the MAC adresses, extension needed
        //Test whether it is a Cisco router based on BSSID
        //all MAC address ranges used by Cisco - source: http://www.miniwebtool.com/mac-address-lookup/?s=cisco
        String[] ciscoMACAddresses = new String[]{"00000C", "000142", "000143", "000163", "000164", "000196", "000197", "0001C7", "0001C9", "000216", "000217", "00023D", "00024A", "00024B", "00027D", "00027E", "0002B9", "0002BA", "0002FC", "0002FD", "000331", "000332", "00036B", "00036C", "00039F", "0003A0", "0003E3", "0003E4", "0003FD", "0003FE", "000427", "000428", "00044D", "00044E", "00046D", "00046E", "00049A", "00049B", "0004C0", "0004C1", "0004DD", "0004DE", "000500", "000501", "000531", "000532", "00055E", "00055F", "000573", "000574", "00059A", "00059B", "0005DC", "0005DD", "000628", "00062A", "000652", "000653", "00067C", "0006C1", "0006D6", "0006D7", "0006F6", "00070D", "00070E", "00074F", "000750", "00077D", "000784", "000785", "0007B3", "0007B4", "0007EB", "0007EC", "000820", "000821", "00087C", "00087D", "0008A3", "0008A4", "0008C2", "0008E2", "0008E3", "000911", "000912", "000943", "000944", "00097B", "00097C", "0009B6", "0009B7", "0009E8", "0009E9", "000A41", "000A42", "000A8A", "000A8B", "000AB7", "000AB8", "000AF3", "000AF4", "000B45", "000B46", "000B5F", "000B60", "000B85", "000BBE", "000BBF", "000BFC", "000BFD", "000C30", "000C31", "000C41", "000C85", "000C86", "000CCE", "000CCF", "000D28", "000D29", "000D65", "000D66", "000DBC", "000DBD", "000DEC", "000DED", "000E08", "000E38", "000E39", "000E83", "000E84", "000ED6", "000ED7", "000F23", "000F24", "000F34", "000F35", "000F66", "000F8F", "000F90", "000FF7", "000FF8", "001007", "00100B", "00100D", "001011", "001014", "00101F", "001029", "00102F", "001054", "001079", "00107B", "0010A6", "0010F6", "0010FF", "001120", "001121", "00115C", "00115D", "001192", "001193", "0011BB", "0011BC", "001200", "001201", "001217", "001243", "001244", "00127F", "001280", "0012D9", "0012DA", "001310", "001319", "00131A", "00135F", "001360", "00137F", "001380", "0013C3", "0013C4", "00141B", "00141C", "001469", "00146A", "0014A8", "0014A9", "0014BF", "0014F1", "0014F2", "00152B", "00152C", "001562", "001563", "0015C6", "0015C7", "0015F9", "0015FA", "001646", "001647", "00169C", "00169D", "0016B6", "0016C7", "0016C8", "00170E", "00170F", "00173B", "001759", "00175A", "001794", "001795", "0017DF", "0017E0", "001818", "001819", "001839", "001868", "001873", "001874", "0018B9", "0018BA", "0018F8", "001906", "001907", "00192F", "001930", "001947", "001955", "001956", "0019A9", "0019AA", "0019E7", "0019E8", "001A2F", "001A30", "001A6C", "001A6D", "001A70", "001AA1", "001AA2", "001AE2", "001AE3", "001B0C", "001B0D", "001B2A", "001B2B", "001B53", "001B54", "001B8F", "001B90", "001BD4", "001BD5", "001BD7", "001C0E", "001C0F", "001C10", "001C57", "001C58", "001CB0", "001CB1", "001CF6", "001CF9", "001D45", "001D46", "001D70", "001D71", "001D7E", "001DA1", "001DA2", "001DE5", "001DE6", "001E13", "001E14", "001E49", "001E4A", "001E6B", "001E79", "001E7A", "001EBD", "001EBE", "001EE5", "001EF6", "001EF7", "001F26", "001F27", "001F6C", "001F6D", "001F9D", "001F9E", "001FC9", "001FCA", "00211B", "00211C", "002129", "002155", "002156", "0021A0", "0021A1", "0021BE", "0021D7", "0021D8", "00220C", "00220D", "00223A", "002255", "002256", "00226B", "002290", "002291", "0022BD", "0022BE", "0022CE", "002304", "002305", "002333", "002334", "00235D", "00235E", "002369", "0023AB", "0023AC", "0023BE", "0023EA", "0023EB", "002413", "002414", "002450", "002451", "002497", "002498", "0024C3", "0024C4", "0024F7", "0024F9", "00252E", "002545", "002546", "002583", "002584", "00259C", "0025B4", "0025B5", "00260A", "00260B", "002651", "002652", "002698", "002699", "0026CA", "0026CB", "00270C", "00270D", "003019", "003024", "003040", "003071", "003078", "00307B", "003080", "003085", "003094", "003096", "0030A3", "0030B6", "0030F2", "003A98", "003A99", "003A9A", "003A9B", "003A9C", "00400B", "004096", "00500B", "00500F", "005014", "00502A", "00503E", "005050", "005053", "005054", "005073", "005080", "0050A2", "0050A7", "0050BD", "0050D1", "0050E2", "0050F0", "006009", "00602F", "00603E", "006047", "00605C", "006070", "006083", "006440", "00900C", "009021", "00902B", "00905F", "00906D", "00906F", "009086", "009092", "0090A6", "0090AB", "0090B1", "0090BF", "0090D9", "0090F2", "00B04A", "00B064", "00B08E", "00B0C2", "00D006", "00D058", "00D063", "00D079", "00D090", "00D097", "00D0BA", "00D0BB", "00D0BC", "00D0C0", "00D0D3", "00D0E4", "00D0FF", "00E014", "00E01E", "00E034", "00E04F", "00E08F", "00E0A3", "00E0B0", "00E0F7", "00E0F9", "00E0FE", "04C5A4", "04FE7F", "081735", "081FF3", "108CCF", "18EF63", "1C17D3", "1CAA07", "1CDF0F", "203706", "2893FE", "3037A6", "30E4DB", "38C85C", "3CDF1E", "405539", "40F4EC", "445829", "44E4D9", "484487", "503DE5", "5475D0", "547FEE", "54D46F", "5835D9", "586D8F", "588D09", "58BC27", "602AD0", "6400F1", "64168D", "687F74", "68BDAB", "68EFBD", "6C504D", "8843E1", "88F077", "8CB64F", "98FC11", "9C4E20", "9CAFCA", "A40CC3", "A8B1D4", "ACA016", "B41489", "B4A4E3", "B8621F", "B8BEBF", "C0626B", "C0C1C0", "C471FE", "C47D4F", "C84C75", "C89C1D", "D0574C", "D0C282", "D0D0FD", "DC7B94", "E05FB9", "E448C7", "E80462", "E84040", "E8B748", "E8BA70", "EC3091", "EC4476", "ECC882", "F02572", "F45FD4", "F4ACC1", "F866F2", "FCFBFB"};
        String first6CharOfBSSID = this.BSSID.replace(":","").substring(0,6).toUpperCase();
        if(!Arrays.asList(ciscoMACAddresses).contains(first6CharOfBSSID)) {
            return "Not vulnerable, since not a Cisco router.";
        }
        */
        return "Possibly vulnerable, tap to add to crack list.";
    }

    public String getPossiblePasswordsAsString(){
        StringBuilder sb = new StringBuilder();
        for (String s : possiblePasswords) {
            sb.append(s);
            sb.append(";");
        }
        return sb.toString();
    }

    public String getStatus() {
        if(serialNumber == CRACK_FINISHED) {
            return "Successfully cracked, password is " + possiblePasswords.get(0);
        }
        if(possiblePasswords.size() > 0) {
            return possiblePasswords.size() + " possible passwords found";
        }
        return "Crack haven't started yet";
    }
}
