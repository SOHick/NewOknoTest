import java.util.ArrayList;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private Pattern p;
    public TextParser(String sPattern){
        p = Pattern.compile(sPattern, Pattern.UNICODE_CHARACTER_CLASS | Pattern.MULTILINE);
    }
    public ArrayList<MatchResult> find(String sText){
        Matcher m = p.matcher(sText);
        var mrs = new ArrayList<MatchResult>();
        while (m.find()){
            var mr = m.toMatchResult();
            mrs.add(mr);
        }
        return mrs;
    }

    public String replace(String sText, String replacement){
        Matcher m = p.matcher(sText);
        return  m.replaceAll(replacement);
    }

    public String replace(String sText, Function<MatchResult, String> rf){
        Matcher m = p.matcher(sText);
        return  m.replaceAll(rf);
    }
}