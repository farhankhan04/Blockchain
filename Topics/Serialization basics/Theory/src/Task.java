import java.io.Serializable;

class User implements Serializable{
  String name;
  transient String  password;
}