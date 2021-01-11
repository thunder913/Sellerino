package com.project.sap;


import com.project.sap.models.Dto.ProcessorDto;
import com.project.sap.models.Dto.RamDto;
import com.project.sap.models.Dto.SaleDto;
import com.project.sap.models.Processor;
import com.project.sap.models.RAM;
import com.project.sap.utils.ProcessorMapper;
import com.project.sap.utils.RamMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTests {

    @Test
    public void processorMapperWorksCorrectly(){
        ProcessorDto processorDto = new ProcessorDto();
        processorDto.setName("Test");
        processorDto.setCores("10");
        processorDto.setManufacturer("TestMan");
        processorDto.setModel("TestMod");
        processorDto.setSpeed("10");
        Processor processor = ProcessorMapper.INSTANCE.processorDtoToProcessor(processorDto);

        assertThat(processorDto.getCores().equals(processor.getCores()));
        assertThat(processorDto.getSpeed().equals(processor.getSpeed()));
        assertThat(processorDto.getManufacturer().equals(processor.getManufacturer()));
        assertThat(processorDto.getModel().equals(processor.getModel()));
        assertThat(processorDto.getName().equals(processor.getName()));
    }

    @Test
    public void processorMapperReturnsNullWhenGivenNullArgument(){
        Processor processor = ProcessorMapper.INSTANCE.processorDtoToProcessor(null);
        assertThat(processor==null);
    }

    @Test
    public void ramMapperWorksCorrectly(){
        RamDto ramDto = new RamDto();
        ramDto.setManufacturer("TestMan");
        ramDto.setMemory("12");
        RAM ram = RamMapper.INSTANCE.ramDtoToRam(ramDto);
        assertThat(ramDto.getMemory().equals(ram.getMemory()));
        assertThat(ramDto.getManufacturer().equals(ram.getManufacturer()));
    }

    @Test
    public void ramMapperReturnsNullWhenGivenNullArgument(){
        RAM ram = RamMapper.INSTANCE.ramDtoToRam(null);
        assertThat(ram == null);
    }
}
