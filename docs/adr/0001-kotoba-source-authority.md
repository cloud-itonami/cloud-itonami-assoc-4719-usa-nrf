# ADR 0001: Kotoba is the NRF catalog source authority

- Status: Accepted
- Date: 2026-07-21

## Decision

`src/association_facts.kotoba` is the sole production source. Both citations
retain every present scalar field. The loss-prevention guide continues to omit
an unverified date, while the organization profile retains year-only `1911`.
Topic count plus indexed access preserves the ordered loss-prevention and
retail-security pair and the governance singleton. Unknown values and indexes
return zero or typed option-none; no effects are declared.

CI executes reference semantics, restricted JavaScript, instantiated typed
WebAssembly, and production source-authority checks. Clojure and the JVM are
compiler/test hosts only.

## Consequences

- Missing and year-only dates retain their source precision.
- Multi-topic entries remain complete without host sets.
