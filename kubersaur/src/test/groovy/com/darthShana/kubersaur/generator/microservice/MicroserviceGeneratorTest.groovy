package com.darthShana.kubersaur.generator.microservice


import com.darthShana.kubersaur.model.Org
import org.kubersaur.codegen.Language
import spock.lang.Specification

class MicroserviceGeneratorTest extends Specification {

    def "get implementation providers"() {
        given:
        def rel = new MicroserviceGenerator("customer", Language.JAVA, new Org(name: "kubersaurus"), new HashSet<String>())

        when:
        rel.init()

        then:
        rel.name == "customer"

    }
}
