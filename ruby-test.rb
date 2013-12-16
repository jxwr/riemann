require 'java'

# namespace
def clojure
  Java::Clojure
end

def riemann
  Java::Riemann
end

java_import clojure.lang.Keyword

# test methods

def hello()
  "hello"
end

def echo(e)
  e
end

def add(a, b)
  a + b
end

def _(name)
  # clojure keyword wraper
  Keyword.intern(name)
end

def host(event)
  # rimann event as hash
  p event[_("func")]
  top = event[_("func")]
  p event[_("where")]
  event[_("host")]
end
