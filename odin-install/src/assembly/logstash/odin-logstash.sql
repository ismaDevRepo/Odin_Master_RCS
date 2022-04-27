SELECT -- JSON_OBJECT('chromosome', JSON_OBJECT('genomeBuild', JSON_OBJECT('libelle', gb.LIBELLE))) AS genomeBuild,
       JSON_OBJECT('variant', JSON_OBJECT('id', result.VARIANT_ID, 'clinicalSignificance', JSON_OBJECT('id', cs.ID, 'name', cs.NAME)))             AS variant,
       -- JSON_OBJECT('gene', JSON_OBJECT('name', g.NAME)) AS gene,
       -- JSON_OBJECT('transcript', JSON_OBJECT('name', t.NAME)) AS transcript,
       JSON_OBJECT('chromosome', JSON_OBJECT('id', result.CHROMOSOME_ID, 'libelle', c.LIBELLE, 'genomeBuild', JSON_OBJECT('libelle', gb.LIBELLE))) AS chromosome,
       result.GENOMIC_START                                                                                                                        AS genomicStart,
       result.GENOMIC_STOP                                                                                                                         AS genomicStop,
       result.REFERENCE_ALLELE                                                                                                                     AS referenceAllele,
       result.ALTERNATIVE_ALLELE                                                                                                                   AS alternativeAllele,
       JSON_OBJECT('annotation', ANNOTATION)                                                                                                       AS annotation,
       JSON_OBJECT('genotype', GENOTYPE)                                                                                                           AS genotype,
       rec.RECURRENCE                                                                                                                              AS recurrence
FROM result
         LEFT JOIN (
    SELECT VARIANT_ID, COUNT(*) AS RECURRENCE
    FROM result
    GROUP BY VARIANT_ID) rec ON result.VARIANT_ID = rec.VARIANT_ID
         JOIN chromosome c on result.CHROMOSOME_ID = c.ID
         JOIN genome_build gb on c.GENOME_BUILD_ID = gb.ID
         JOIN variant v on result.VARIANT_ID = v.ID
         LEFT JOIN clinical_significance cs on v.CLINICAL_SIGNIFICANCE_ID = cs.ID
         JOIN import i on result.IMPORT_ID = i.ID
WHERE i.END_DATE > :sql_last_value;
