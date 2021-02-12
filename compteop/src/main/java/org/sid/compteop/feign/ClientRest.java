package org.sid.compteop.feign;


import org.sid.compteop.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CLIENT-SERVICE")
public interface ClientRest {
    @GetMapping(path="/clients/{id}")
    Client getClientById(@PathVariable(name = "id") Long id);

    @GetMapping(path="/clients")
    PagedModel<Client> pageClient(@RequestParam(name = "page")int page, @RequestParam(name = "size")int size);
}
