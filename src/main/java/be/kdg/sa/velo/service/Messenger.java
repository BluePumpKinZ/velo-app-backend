package be.kdg.sa.velo.service;

public interface Messenger<T> {
  public void sendMessage(T location);
}
