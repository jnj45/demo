package demo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SimpleCodeTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("ecbank1!"));
	}

}
