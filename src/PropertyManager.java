import java.io.*;

public class PropertyManager implements Serializable {
    private int boardSize;
    private Enums.PlayersColors firstPlayerColor;
    private Enums.PlayersColors secondPlayerColor;
    private Enums.StartFirstOptions startFirstOptions;

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setFirstPlayerColor(Enums.PlayersColors firstPlayerColor) {
        this.firstPlayerColor = firstPlayerColor;
    }

    public void setSecondPlayerColor(Enums.PlayersColors secondPlayerColor) {
        this.secondPlayerColor = secondPlayerColor;
    }

    public void setStartFirstOptions(Enums.StartFirstOptions startFirstOptions) {
        this.startFirstOptions = startFirstOptions;
    }

    PropertyManager(String fileName) {
        File propertyFileManager = new File(fileName);

        //Check if file exists.
        if (propertyFileManager.exists()) {
            try {
                //If it exists, load it.
                load(propertyFileManager);
            } catch (Exception e) {
                System.out.println("Failed to load file.");
            }
        } else {
            try {
                //If it's not exists, save new file.
                setParametersToDefault();

                save(propertyFileManager);
            } catch (Exception e) {
                System.out.println("Can't save new file.");
            }
        }

    }

    /**
     * Save file property data to the specified file.
     *
     * @param filename Filename of property manager.
     * @throws IOException Exception if file can't be loaded.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            //Create object input steam that decorate file input stream.
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename.getPath()));
            //Write object to file.
            objectOutputStream.writeObject(this);

        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getPath());
            }
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Enums.PlayersColors getFirstPlayerColor() {
        return firstPlayerColor;
    }

    public Enums.PlayersColors getSecondPlayerColor() {
        return secondPlayerColor;
    }

    public Enums.StartFirstOptions getStartFirstOptions() {
        return startFirstOptions;
    }

    /**
     * Load property data from file.
     * Current table data is cleared.
     *
     * @param filename Filename of property.
     * @throws IOException Exception if file can't be loaded.
     */
    public void load(File filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            //Create object input steam that decorate file input stream.
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename.getPath()));
            //Read object as high score table.
            PropertyManager loadedPropertyManager = (PropertyManager) objectInputStream.readObject();

            //Set this table to be high score that loaded.
            this.boardSize = loadedPropertyManager.getBoardSize();
            this.firstPlayerColor = loadedPropertyManager.getFirstPlayerColor();
            this.secondPlayerColor = loadedPropertyManager.getSecondPlayerColor();
            this.startFirstOptions = loadedPropertyManager.getStartFirstOptions();

        } catch (FileNotFoundException e) {
            //Can't find file to open.
            System.err.println("Unable to find file: " + filename.getPath());
            return;
        } catch (ClassNotFoundException e) {
            //The class in the stream is unknown to the JVM.
            System.err.println("Unable to find class for object in file: " + filename.getPath());
            return;
        } catch (IOException e) {
            //Some other problem.
            System.err.println("Failed reading object");
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getPath());
            }
        }
    }

    private void setParametersToDefault(){
        final Enums.StartFirstOptions startFirstOptionsDef = Enums.StartFirstOptions.Player1First;
        final Enums.PlayersColors firstPlayerColorDef = Enums.PlayersColors.White;
        final Enums.PlayersColors secondPlayerColorDef = Enums.PlayersColors.Black;
        final int boardSizeDef = 4;

        this.startFirstOptions = startFirstOptionsDef;
        this.firstPlayerColor = firstPlayerColorDef;
        this.secondPlayerColor = secondPlayerColorDef;
        this.boardSize = boardSizeDef;
    }

}
