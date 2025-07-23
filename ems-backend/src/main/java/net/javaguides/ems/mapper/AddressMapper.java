package net.javaguides.ems.mapper;
import net.javaguides.ems.dto.Addressdto;
import net.javaguides.ems.entity.Address;

public class AddressMapper {

    public static Addressdto mapToAddressdto(Address address)
    {
        return new Addressdto(address.getAddrid(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipcode(),
                address.getEmployee()
        );

    }
    public static Address mapToAddress(Addressdto dto)
    {
        Address address=new Address();
        address.setAddrid(dto.getAddrid());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipcode(dto.getZipcode());
        return address;
    }
}
