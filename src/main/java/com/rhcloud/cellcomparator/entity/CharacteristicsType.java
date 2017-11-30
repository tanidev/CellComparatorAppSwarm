package com.rhcloud.cellcomparator.entity;

import java.util.Comparator;

public enum CharacteristicsType {
	
	OPERATIONAL_SYSTEM(Boolean.FALSE, "Sistema operacional") {
		@Override
		public Comparator<Characteristic> getComparator() {
			// TODO Auto-generated method stub
			return null;
		}
	}, 
	OS_VERSION(Boolean.FALSE, "Versão") {
		@Override
		public Comparator<Characteristic> getComparator() {
			// TODO Auto-generated method stub
			return null;
		}
	}, 
	
	STORAGE(Boolean.TRUE, "Memória interna") {
		@Override
		public Comparator<Characteristic> getComparator() {
			return new Comparator<Characteristic>() {
				@Override
				public int compare(Characteristic o1, Characteristic o2) {
					
					return Integer.compare(Integer.valueOf(o1.getValue()), 
							Integer.valueOf(o2.getValue()));
				}
			};
		}
	}, 
	RAM_MEMORY(Boolean.TRUE, "Memoria RAM") {
		@Override
		public Comparator<Characteristic> getComparator() {
			return new Comparator<Characteristic>() {
				@Override
				public int compare(Characteristic o1, Characteristic o2) {
					
					return Integer.compare(Integer.valueOf(o1.getValue()), 
							Integer.valueOf(o2.getValue()));
				}
			};
		}
	}, 
	FRONTAL_CAMERA(Boolean.TRUE, "Câmera frontal") {
		@Override
		public Comparator<Characteristic> getComparator() {
			return new Comparator<Characteristic>() {
				@Override
				public int compare(Characteristic o1, Characteristic o2) {
					
					return Double.compare(Double.valueOf(o1.getValue()), 
							Double.valueOf(o2.getValue()));
				}
			};
		}
	}, 
	CAMERA(Boolean.TRUE, "Câmera") {
		@Override
		public Comparator<Characteristic> getComparator() {
			return new Comparator<Characteristic>() {
				@Override
				public int compare(Characteristic o1, Characteristic o2) {
					
					return Double.compare(Double.valueOf(o1.getValue()), 
							Double.valueOf(o2.getValue()));
				}
			};
		}
	}, 
	DISPLAY_TYPE(Boolean.FALSE, "Tela") {
		@Override
		public Comparator<Characteristic> getComparator() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	DISPLAY_SIZE(Boolean.TRUE, "Tamanho tela") {
		@Override
		public Comparator<Characteristic> getComparator() {
			return new Comparator<Characteristic>() {
				@Override
				public int compare(Characteristic o1, Characteristic o2) {
					
					return Double.compare(Double.valueOf(o1.getValue()), 
							Double.valueOf(o2.getValue()));
				}
			};
		}
	}, 
	BATTERY_CAPACITY(Boolean.TRUE, "Capacidade bateria") {
		@Override
		public Comparator<Characteristic> getComparator() {
			return new Comparator<Characteristic>() {
				@Override
				public int compare(Characteristic o1, Characteristic o2) {
					
					return Integer.compare(Integer.valueOf(o1.getValue()), 
							Integer.valueOf(o2.getValue()));
				}
			};
		}
	},
	BATTERY_STAND_BY_TIME(Boolean.TRUE, "Bateria em stand by") {
		@Override
		public Comparator<Characteristic> getComparator() {
			return new Comparator<Characteristic>() {
				@Override
				public int compare(Characteristic o1, Characteristic o2) {
					
					return Integer.compare(Integer.valueOf(o1.getValue()), 
							Integer.valueOf(o2.getValue()));
				}
			};
		}
	};
	
	private boolean comparable;
	private String typeName;
	
	private CharacteristicsType(boolean comparable, String typeName) {
		this.comparable = comparable;
		this.typeName = typeName;
	}
	
	public boolean isComparable() {
		return comparable;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public abstract Comparator<Characteristic> getComparator();

}
