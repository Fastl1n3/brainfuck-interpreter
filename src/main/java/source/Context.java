package source;

public class Context {
    private byte[] arr = new byte[30000];
    private int i = 0;
    private String strCode;
    private int iterCode = 0;
    public Context(String strCode) {
        this.strCode = strCode;
    }
    public void incIdx() {
        i++;
    }
    public void decIdx() {
        i--;
    }
    public byte getByte() {
        return arr[i];
    }
    public void setByte(byte elem) {
        this.arr[i] = elem;
    }
    public void incItrCode() {
        iterCode++;
    }
    public void decItrCode() {
        iterCode--;
    }
    public char getElem() {
        return strCode.charAt(iterCode);
    }
    public boolean isEnd() {
        return iterCode >= strCode.length();
    }
}
