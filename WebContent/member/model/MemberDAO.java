package model;

public class MemberDAO{
    private static MemberDAO(){
    }

    public static MemberDAO getInstance(){
        if(instance == null){
            instance = new MemberDAO();
        }
        return instance;
    }
}