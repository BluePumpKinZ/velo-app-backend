package be.kdg.sa.velo.services;

public interface Messenger<T> {
  public void sendMessage(T location);
}
