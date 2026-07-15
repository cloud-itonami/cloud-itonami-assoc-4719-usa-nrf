(ns association.facts
  "Industry rule/best-practices catalog for the National Retail
  Federation (NRF, Wikidata Q6978097) -- a 20th industry-association-level
  source (see cloud-itonami-assoc-6419-jpn-zenginkyo, -6512-jpn-sonpo,
  -6612-jpn-jsda, -6419-deu-bankenverband, -6612-usa-finra,
  -6512-usa-naic, -6920-jpn-jicpa, -6920-usa-aicpa, -6419-fra-fbf,
  -6511-jpn-seiho, -6910-jpn-nichibenren, -6810-jpn-recaj, -6411-jpn-boj,
  -6120-usa-ctia, -5110-usa-a4a, -3510-usa-eei, -2910-deu-vda,
  -5510-usa-ahla, -2100-usa-phrma for the first nineteen) per
  ADR-2607141700 (cloud-itonami-compliance-fact-federation). The FIRST
  entry aligned to ISIC 4719 (other retail sale in non-specialized
  stores) -- a new industry code for this family. A rule not in this
  table has NO spec-basis, full stop; extend `catalog`, do not invent
  an id/url.

  BSA | The Software Alliance (bsa.org) was tried first for an IT/software
  association-axis entry but returned HTTP 403 on every page attempted
  -- abandoned without fabricating a citation, and NRF was used instead.
  Both NRF entries were directly WebFetch-verified: 'Five to Thrive:
  Loss Prevention' (a genuine NRF-authored practical-guidance resource,
  not a binding member commitment, so a new `:kind :best-practices-guide`
  is introduced to preserve that distinction) and the About Us profile
  page (the 1911 founding year is WebSearch/Wikipedia-corroborated, not
  stated on the page itself, which only says 'over a century').")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"nrf"
   [{:association-rule/id "nrf.five-to-thrive-loss-prevention"
     :association-rule/title "Five to Thrive: Loss Prevention"
     :association-rule/association "nrf"
     :association-rule/isic "4719"
     :association-rule/country "USA"
     :association-rule/kind :best-practices-guide
     :association-rule/url "https://nrf.com/resources/center-small-retail-businesses/five-to-thrive-resources/five-to-thrive-loss-prevention"
     :association-rule/url-provenance :official-association-site
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:loss-prevention :retail-security}}
    {:association-rule/id "nrf.about-profile"
     :association-rule/title "About Us (organization profile)"
     :association-rule/association "nrf"
     :association-rule/isic "4719"
     :association-rule/country "USA"
     :association-rule/kind :governance-program
     :association-rule/url "https://nrf.com/about-us"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1911"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:governance}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-4719-usa-nrf Wave 0 (ADR-2607141700): "
                 (count (get catalog "nrf")) " nrf entries seeded with an "
                 "official nrf.com citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
