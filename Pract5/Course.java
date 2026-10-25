// Общий файл для заданий: 3, 9, 10

public class Course{
        private final String id;
        private String title;

        public Course(String id, String title){
            this.id = id;
            this.title = title;
        }

        public String getId(){
            return id;
        }

        public String getTitle(){
            return title;
        }

        public void setTitle(String title){
            this.title = title;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof Course))
                return false;
            return id.equals(((Course) o).id);
        }

        @Override
        public int hashCode(){
            return id.hashCode();
        //    return java.util.Objects.hash(id, title);     //Для ошибки в 10-ом номере.
        }
        
        @Override
        public String toString(){
            return id + ":" + title;
        }
}