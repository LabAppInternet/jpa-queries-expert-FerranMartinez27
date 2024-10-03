package cat.tecnocampus.fgcstations.application.DTOs;

public record StationDTO(String name, String longitud, String latitud) implements StationDTOInterface {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getLongitud() {
        return "";
    }

    @Override
    public String getLatitud() {
        return "";
    }
}
