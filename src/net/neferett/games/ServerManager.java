package net.neferett.games;

import java.io.*;

import net.neferett.*;
import net.neferett.executor.*;
import net.neferett.utils.*;

public class ServerManager
{
    private String dir;
    private int id;
    private final String jar;
    private final String javadir;
    private final String maxRam;
    private final String name;
    private final String path;
    private final int port;
    private final String stopcmd;
    private final boolean update;
    private final Utils utils;
    
    ServerManager(final int id, final String name, final String path, final String jar, final String maxRam, final int port, final boolean update, final String stopcmd) {
        this.id = 0;
        this.id = id;
        this.javadir = System.getProperty("java.home");
        this.utils = new Utils();
        this.name = name;
        this.path = path;
        this.jar = jar;
        this.update = update;
        this.maxRam = maxRam;
        this.stopcmd = stopcmd;
        this.dir = new File(".").getAbsolutePath();
        this.dir = this.dir.substring(0, this.dir.length() - 1);
        this.port = port;
        this.utils.ConsoleMessage("Registering " + name + " with port " + port);
        this.CreateServer();
    }
    
    private String[] CmdRet(final String cmd) {
        return ("screen -S " + this.getScreen() + " -p 0 -X stuff " + cmd + "\n").split(" ");
    }
    
    private void CreateServer() {
        this.utils.ConsoleMessage("Creating server " + this.name + " in " + "running/" + this.name + "/" + this.id);
        new File(this.getRunningPath()).mkdirs();
        new Execute("cp", "-r", String.valueOf(this.path) + "/.", this.getRunningPath()).ExecuteCommand();
        if (this.jar.contains("spigot") && this.update) {
            new Execute("cp", "-r", Main.getInstance().getLibspath(), String.valueOf(this.getRunningPath()) + "/plugins/.").ExecuteCommand();
        }
    }
    
    private String getJRE() {
        if (this.javadir == null) {
            throw new IllegalStateException("java.home");
        }
        return new File(this.javadir, "bin/java").toString();
    }
    
    private String getRunningPath() {
        return String.valueOf(this.dir) + "running/" + this.name + "/" + this.id + "/";
    }
    
    private String getScreen() {
        return String.valueOf(this.name) + "-" + this.id;
    }
    
    private String[] JavaLaunch() {
        return ("screen -dmS " + this.getScreen() + " " + this.getJRE() + " -jar -Xmx" + this.maxRam + " " + this.getRunningPath() + this.jar + (this.port != -1 ? " -p " + this.port : "")).split(" ");
    }
    
    private void LaunchCommand(final String cmd) {
        new Execute(this.CmdRet(cmd)).ExecuteCommand();
    }
    
    void LaunchServer() {
        this.utils.ConsoleMessage("Launching " + this.name + " " + this.id + " on SCREEN : " + this.getScreen());
        new Execute(this.JavaLaunch()).ExecuteCommand(new File(this.getRunningPath()));
    }
    
    void RemoveServer() {
        new Execute("rm", "-rf", this.getRunningPath()).ExecuteCommand();
    }
    
    public void stop() {
        this.LaunchCommand(this.stopcmd);
    }
}
