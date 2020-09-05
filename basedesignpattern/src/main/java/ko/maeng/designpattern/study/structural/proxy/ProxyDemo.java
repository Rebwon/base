package ko.maeng.designpattern.study.structural.proxy;

public class ProxyDemo {

  public static void main(String[] args) {
    Image image1 = new ProxyImage("Belgium.png");
    Image image2 = new ProxyImage("SouthKorea.png");

    image1.displayImage();
    image2.displayImage();
  }
}

interface Image {
  void displayImage();
}

class ProxyImage implements Image {
  private String fileName;
  private Image image;

  public ProxyImage(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void displayImage() {
    if(image == null) {
      image = new RealImage(fileName);
    }
    image.displayImage();
  }
}


class RealImage implements Image {
  private String fileName;

  public RealImage(String fileName) {
    this.fileName = fileName;
    loadImageFromDisk();
  }

  private void loadImageFromDisk() {
    System.out.println("Loading: " + fileName);
  }

  @Override
  public void displayImage() {
    System.out.println("Displaying: " + fileName);
  }
}
