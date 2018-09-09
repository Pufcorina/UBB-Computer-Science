package ro.ubb.donation.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.model.Container;
import ro.ubb.donation.core.service.CenterService;
import ro.ubb.donation.core.service.ContainerService;
import ro.ubb.donation.web.converter.ContainerConverter;
import ro.ubb.donation.web.dto.ContainerDto;
import ro.ubb.donation.web.requests.ContainerForm;
import ro.ubb.donation.web.response.CentersResponse;
import ro.ubb.donation.web.response.ContainerResponse;
import ro.ubb.donation.web.response.OurStockResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ContainerController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ContainerService containerService;

    @Autowired
    private CenterService centerService;

    @Autowired
    private ContainerConverter containerConverter;
    private List<Container> containers;


    @RequestMapping(value = "/containers/{centerId}", method = RequestMethod.DELETE)
    public ContainerResponse removeExpiredContainersByCenter(@PathVariable int centerId){
        try{
            List<Container> containers = containerService.getContainersByCenterId(centerId);
            Date currentDate = new Date();
            System.out.println(currentDate);
            List<Container> expiredContainers = containers.stream().filter( c -> c.getExpirationDate().compareTo(currentDate) < 0).collect( Collectors.toList() );
            expiredContainers.forEach( c -> containerService.deleteContainer( c.getId() ) );
            return ContainerResponse.builder()
                    .status("Success")
                    .message("Containers by centerId deleted")
                    .isError(true)
                    .build();
        } catch (Exception e){
            return ContainerResponse.builder()
                    .status("Error")
                    .message(e.getMessage())
                    .isError(true)
                    .build();
        }
    }

    @RequestMapping(value = "/diminish-stock/{centerId}", method = RequestMethod.POST)
    public ContainerResponse removeFirstNContainersByCenter(@RequestBody ContainerForm containerForm, @PathVariable int centerId){
        try{
            List<Container> containers = containerService.getContainersByCenterId(centerId);
            if (containerForm.getContainerDto().getComponentType().equals( "Red cells" ))
                containers = containers.stream()
                        .filter( c -> c.getComponentType().equals( containerForm.getContainerDto().getComponentType() ) &&
                                c.getBloodGroup().equals( containerForm.getContainerDto().getBloodGroup() )  &&
                                c.getRh().equals( containerForm.getContainerDto().getRh() ))
                        .collect( Collectors.toList() );
            else
                containers = containers.stream()
                        .filter( c -> c.getComponentType().equals( containerForm.getContainerDto().getComponentType() ) )
                        .collect( Collectors.toList() );
            containers.sort( Comparator.comparing( Container::getExpirationDate ) );

            Integer nrContainers = containerForm.getHowManyToRemove();
            while (nrContainers != 0){
                Container container = containers.get( 0 );
                containerService.deleteContainer( container.getId() );
                containers.remove( 0 );
                nrContainers--;
            }
            return ContainerResponse.builder()
                    .status("Success")
                    .message("Containers deleted")
                    .isError(false)
                    .build();
        } catch (Exception e){
            return ContainerResponse.builder()
                    .status("Error")
                    .message(e.getMessage())
                    .isError(true)
                    .build();
        }
    }

    @RequestMapping(value = "/replenish-stock/{centerId}", method = RequestMethod.POST)
    public ContainerResponse createContainer(@RequestBody ContainerForm containerForm, @PathVariable int centerId)
    {
        try{
            Optional<Center> center = this.centerService.findCenter(centerId);
            DateFormat format = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);
            Date date = format.parse(containerForm.getContainerDto().getExpirationDate());
            center.ifPresent(center1 -> this.containerService.createContainer(date, center.get(),
                    containerForm.getContainerDto().getBloodGroup(),containerForm.getContainerDto().getRh(),containerForm.getContainerDto().getComponentType()));
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ContainerResponse.builder().message("Container added successfully").status("success").build();
    }

/*
    @RequestMapping(value = "/containers/{centerId}", method = RequestMethod.GET)
    public Set<ContainerDto> getAllContainersExpiredByCenter(@PathVariable int centerId) {
        List<Container> containers = containerService.getContainersByCenterId(centerId);
        Date currentDate = new Date();
        System.out.println(currentDate);
        List<Container> expiredContainers = containers.stream().filter( c -> c.getExpirationDate().compareTo(currentDate) < 0).collect( Collectors.toList() );
        Set<ContainerDto> containerDtos = new HashSet<>( containerConverter.convertModelsToDtos( expiredContainers ) );

        return containerDtos;
    }
*/

//    @RequestMapping(value = "/containers/{centerId}", method = RequestMethod.GET)
//    public Set<ContainerDto> getAllContainersByCenter(@PathVariable int centerId) {
//        List<Container> containers = containerService.getContainersByCenterId(centerId);
//        Set<ContainerDto> containerDtos = new HashSet<>( containerConverter.convertModelsToDtos( containers ) );
//
//        return containerDtos;
//    }

    @RequestMapping(value = "/containers/{centerId}", method = RequestMethod.GET)
    public OurStockResponse getAllContainersByCenter(@PathVariable int centerId) {
        return OurStockResponse.builder()
                .APositiveExpired(this.containerService.getNrContainersExpired("redCells","A","+",centerId))
                .APositiveUsable(this.containerService.getNrContainersUsable("redCells","A","+",centerId))
                .ANegativeExpired(this.containerService.getNrContainersExpired("redCells","A","-",centerId))
                .ANegativeUsable(this.containerService.getNrContainersUsable("redCells","A","-",centerId))
                .BPositiveExpired(this.containerService.getNrContainersExpired("redCells","B","+",centerId))
                .BPositiveUsable(this.containerService.getNrContainersUsable("redCells","B","+",centerId))
                .BNegativeExpired(this.containerService.getNrContainersExpired("redCells","B","-",centerId))
                .BNegativeUsable(this.containerService.getNrContainersUsable("redCells","B","-",centerId))
                .ABPositiveExpired(this.containerService.getNrContainersExpired("redCells","AB","+",centerId))
                .ABPositiveUsable(this.containerService.getNrContainersUsable("redCells","AB","+",centerId))
                .ABNegativeExpired(this.containerService.getNrContainersExpired("redCells","AB","-",centerId))
                .ABNegativeUsable(this.containerService.getNrContainersUsable("redCells","AB","-",centerId))
                .zeroPositiveExpired(this.containerService.getNrContainersExpired("redCells","0","+",centerId))
                .zeroPositiveUsable(this.containerService.getNrContainersUsable("redCells","0","+",centerId))
                .zeroNegativeExpired(this.containerService.getNrContainersExpired("redCells","0","-",centerId))
                .zeroNegativeUsable(this.containerService.getNrContainersUsable("redCells","0","-",centerId))
                .plasmaExpired(this.containerService.getNrContainersExpired("plasma",centerId))
                .plasmaUsable(this.containerService.getNrContainersUsable("plasma",centerId))
                .thrombocyteExpired(this.containerService.getNrContainersExpired("thrombocytes",centerId))
                .thrombocyteUsable(this.containerService.getNrContainersUsable("thrombocytes",centerId))
                .build();
    }


    @RequestMapping(value = "/containers", method = RequestMethod.GET)
    public Set<ContainerDto> getAllContainers() {
        List<Container> containers = containerService.findAll();
        Set<ContainerDto> containerDtos = new HashSet<>( containerConverter.convertModelsToDtos( containers ) );

        return containerDtos;
    }
}
