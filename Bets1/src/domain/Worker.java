package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Worker {

	@Id
		private String log;
		private String pass;
				
		public Worker() {
			
		}
		public Worker(String log, String pass) {
			super();
			this.log = log;
			this.pass = pass;
			}

		public String getLog() {
			return log;
		}

		public void setLog(String log) {
			this.log = log;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}
}
