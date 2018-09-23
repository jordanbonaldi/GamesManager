package net.neferett.executor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class ExecutorManager {

	protected ProcessBuilder	pb;
	protected Process			process;
	protected String[]			cmd;
	protected InputStream		in;
	protected byte[]			buff;
	
	public ExecutorManager(String... cmd){
		this.buff = new byte[1024];
		this.cmd = cmd;
	}
	
	public void start(){
		try {
			this.process = pb.start();
			in = this.process.getInputStream();
			while (true){
				int read = 0;
				if ((read = in.read(this.buff)) <= 0)
					break;
				System.out.write(buff, 0, read);
			};
			try {
				this.process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public ProcessBuilder getProcessBuilder(){
		return (new ProcessBuilder(this.cmd));
	}
	
	public void ExecuteCommand(){
		this.pb = getProcessBuilder();
		this.pb.redirectErrorStream(true);
		start();
	}
	
	public void ExecuteCommand(File dir){
		this.pb = getProcessBuilder();
		this.pb.directory(dir);
		this.pb.redirectErrorStream(true);
		start();
	}
	
}
