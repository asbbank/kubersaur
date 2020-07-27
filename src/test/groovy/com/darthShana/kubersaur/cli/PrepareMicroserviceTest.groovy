package com.darthShana.kubersaur.cli

import com.darthShana.kubersaur.model.Org
import org.yaml.snakeyaml.Yaml

class PrepareMicroserviceTest extends spock.lang.Specification {

    def "length of Spock's and his friends' names"() {
        given:
        def rel = new PrepareMicroservice()
        rel.manifestFile = "./connect-manifest.json"

        when:
        rel.run(new Org(name: "kubersaurus"), null)

        then:
        rel.microservices.size() == 3

    }

}
