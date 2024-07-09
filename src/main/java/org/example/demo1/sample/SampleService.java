package org.example.demo1.sample;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ToString
public class SampleService {

    //DAO가 Service 한테 주입
    @Qualifier("normal")
    private final SampleDAO sampleDAO;
}
