package com.darthShana.kubersaur.generator.microservice


import com.darthShana.kubersaur.model.Org
import spock.lang.Specification

class MicroserviceGeneratorTest extends Specification {

    def "get implementation providers"() {
        given:
        def rel = new MicroserviceGenerator("customer", Language.JAVA, new Org(name: "kubersaurus"))

        when:
        rel.validate()

        then:
        rel.name == "customer"

    }
}
