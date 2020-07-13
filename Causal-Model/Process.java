public class Process {
    String nickname;
    WriteInterface writerInterface;

    public Process(String nickname, WriteInterface writerInterface){
        this.nickname = nickname;
        this.writerInterface = writerInterface;
    }

    public String getNickname(){
        return nickname;
    }

    public WriteInterface getWriter() {
        return writerInterface;
    }

}