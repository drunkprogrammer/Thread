package team.ecut.shenyou.client.protocol;

/**
 * Defined the protocol of the game
 * It can help u a lot to understand this class by reading the protocol document before
 */
public class GameProtocol {

    private String source;  //where the protocol come from
    private String target;  //identify the target device
    private String params;  //parameters

    @Override
    public String toString() {
        return "<" + source + "," + target + "," + params + ">";
    }

    /**
     * Exchange the source device and the target device
     */
    public String exchangeSrcTag(){
        return "<" + target + "," + source + "," + params + ">";
    }

    public GameProtocol(){
        this.source = null;
        this.target = null;
        this.params = null;
    }

    public GameProtocol(String source, String target, String params) {
        this.source = source;
        this.target = target;
        this.params = params;
    }

    public static GameProtocol fromString(String stringProtocol){
        //stringProtocol like be <SERVER,CLIENT,REG>
        String[] strings = stringProtocol.substring(1,stringProtocol.length()-1).split(",");
        return new GameProtocol(strings[0], strings[1], strings[2]);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
