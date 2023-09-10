import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 14406. 좌중을 사로잡는 건배사
 */
public class boj14406 {
    /**
     * toast: 건배사
     */
    static String toast;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        toast = br.readLine();

        if(toast.equals("사.우.나")) {
            System.out.println("사랑과 우정을 나누자");
        } else if(toast.equals("오.징.어")) {
            System.out.println("오래도록 징그럽게 어울리자");
        } else if(toast.equals("사.이.다")) {
            System.out.println("사랑하자 이 세상 다 바쳐");
        } else if(toast.equals("나.가.자")) {
            System.out.println("나라, 가정, 자신의 발전을 위하여");
        } else if(toast.equals("재.개.발")) {
            System.out.println("재미있고 개성있게 발전적으로 살자");
        } else if(toast.equals("우.아.미")) {
            System.out.println("우아하고 아름다운 미래를 위하여");
        } else if(toast.equals("이.기.자")) {
            System.out.println("이런 기회를 자주 만들자");
        } else if(toast.equals("청.바.지")) {
            System.out.println("청춘은 바로 지금부터");
        } else if(toast.equals("걸.걸.걸")) {
            System.out.println("더 사랑할걸, 더 참을걸, 더 즐길걸.");
        } else if(toast.equals("지.화.자")) {
            System.out.println("지금부터 화합하자");
        } else if(toast.equals("재.건.축")) {
            System.out.println("재미있고 건강하게, 축복하며 살자");
        } else if(toast.equals("해.당.화")) {
            System.out.println("해가 갈수록 당당하고 화려하게");
        } else if(toast.equals("주.전.자")) {
            System.out.println("주인의식을 갖고 전문성을 갖추고 자신있게 살자");
        }
    }
}
